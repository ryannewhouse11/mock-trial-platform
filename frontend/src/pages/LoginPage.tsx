import { useState } from 'react'
import { supabase } from '../lib/supabase'

function LoginPage() {
    const[email, setEmail] = useState('')
    const[password, setPassword] = useState('')
    const handleLogin = async (event: React.FormEvent) => {
        event.preventDefault()

        const { error } = await supabase.auth.signInWithPassword({
            email,
            password
        })

        if (error) {
            console.error('Login failed:', error)
            return
        }

        console.log('Login successful')
    }
    return (
        <div>
            <h1>Mock Trial Platform</h1>
            <h2>Login</h2>

            <form onSubmit={handleLogin}>
                <label>
                    Email
                    <input 
                    type="email"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                    />
                </label>

                <br />

                <label>
                    Password
                    <input
                    type="password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    />
                </label>

                <br />

                <button type="submit">
                    Sign In
                    </button>
            </form>
        </div>
    )
}

export default LoginPage