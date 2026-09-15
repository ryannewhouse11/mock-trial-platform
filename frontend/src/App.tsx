import { useEffect, useState} from 'react'
import { supabase } from './lib/supabase'
import type { Session } from '@supabase/supabase-js'

import LoginPage from './pages/LoginPage'

type AppUser = {
  id: string
  authId: string
  name: string
  email: string
}

type Team = {
  id: string
  name: string
  createdAt: string
}

function App() {
  
  const [session, setSession] = useState<Session | null>(null)
  const [appUser, setAppUser] = useState<AppUser | null>(null)
  const [loading, setLoading] = useState(true)
  const [teams, setTeams] = useState<Team[]>([])

  useEffect(() => {
    const loadUser = async (currentSession: Session | null) =>  {
      
      setSession(currentSession)
      setLoading(true)

      if(!currentSession) {
        setTeams([])
        setAppUser(null)
        setLoading(false)
        return
      }

        
      try {
       
        const userResponse = await fetch(
          `http://localhost:8080/api/users/auth/${currentSession.user.id}`
        )

        
        if (!userResponse.ok) {
          throw new Error(`Backed returned ${userResponse.status}`)
        }

        const user: AppUser = await userResponse.json()
       
        setAppUser(user)
        try {
        const teamsResponse = await fetch(
          `http://localhost:8080/api/user/teams/${user.id}`
        )

        if(!teamsResponse.ok) {
          throw new Error(`Backend returned ${teamsResponse.status}`)
        }

        const userTeams: Team[] = await teamsResponse.json()
        setTeams(userTeams)
      } catch (error) {
        console.error('Error loading teams', error)
        setTeams([])
      }

      } catch (error) {
        console.error('Error loading application user:', error)
        setAppUser(null)
      } finally {
       
        setLoading(false)
      }
    }

    supabase.auth.getSession().then(({ data: { session } }) => {
      loadUser(session)
    })
    const {
      data: { subscription },
    } = supabase.auth.onAuthStateChange((_event, session) => {
      setSession(session)
    })

    return () => {
      subscription.unsubscribe()
    }
  }, [])

  if (loading) {
    return <p>Loading...</p>
  }

  if (!session) {
    return <LoginPage />
  }

  if (!appUser) {
    return <p>Unable to load your application account.</p>
  }


  return (
    <div>
      <h1>Welcolme, {appUser?.name}</h1>
      <p>{appUser.email}</p>
      <p>{session.user.id}</p>
      <h2>Your Teams</h2>

      {teams.length === 0 ? (
        <p>You are not currently on a team.</p>
      ) : (
        <ul>
          {teams.map((team) => (
            <li key={team.id}>{team.name}</li>
          ))}
        </ul>
      )}
    </div>
  )
}

export default App