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

function App() {
  
  const [session, setSession] = useState<Session | null>(null)
  const [appUser, setAppUser] = useState<AppUser | null>(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const loadUser = async (currentSession: Session | null) =>  {
      
      setSession(currentSession)

      if(!currentSession) {
        
        setAppUser(null)
        setLoading(false)
        return
      }

        
      try {
       
        const response = await fetch(
          `http://localhost:8080/api/users/auth/${currentSession.user.id}`
        )

        
        if (!response.ok) {
          throw new Error(`Backed returned ${response.status}`)
        }

        const user: AppUser = await response.json()
       
        setAppUser(user)
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
    </div>
  )
}

export default App