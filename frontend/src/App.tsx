import { useEffect, useState} from 'react'

type Team = {
  id: string
  name: string
  createdAt: string
}

function App() {
  const [teams, setTeams] = useState<Team[]>([])

  useEffect(() => {
    fetch('http://localhost:8080/api/teams')
    .then(response => response.json())
    .then(data => setTeams(data))
    .catch(error => console.error('Error fetching teams:', error))
  }, [])

  return (
    <div>
      <h1>Mock Trial Platform</h1>
      <p>Your mock trial preperation workspace.</p>

      <h2>Teams</h2>

      {teams.map(team => (
        <div key={team.id}>
          <h3>{team.name}</h3>
          </div>
      ))}
    </div>
  )
}

export default App