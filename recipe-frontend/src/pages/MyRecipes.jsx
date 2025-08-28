import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../services/api';

export default function MyRecipes() {
  const [list, setList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState('');

  useEffect(() => {
    let cancelled = false;
    (async () => {
      setLoading(true); setErr('');
      try {
        const { data } = await api.get('/recipes'); // GET http://localhost:5173/api/recipes -> proxied to :8080
        if (!cancelled) setList(data || []);
      } catch (e) {
        if (!cancelled) setErr('Unable to load saved recipes.');
        console.error(e);
      } finally {
        if (!cancelled) setLoading(false);
      }
    })();
    return () => { cancelled = true; };
  }, []);

  return (
    <div style={{ padding: '16px 0', maxWidth: 900, margin: '0 auto' }}>
      <h2>My Recipes</h2>
      {err && <p style={{ color: 'crimson' }}>{err}</p>}
      {loading ? (
        <p>Loading…</p>
      ) : list.length === 0 ? (
        <p>
          No saved recipes yet. Go to <Link to="/">Search</Link> and click “Save to My Recipes”.
        </p>
      ) : (
        <ul>
          {list.map(r => (
            <li key={r.id} style={{ marginBottom: 6 }}>
              <Link to={`/my-recipes/${r.id}`}>{r.title}</Link>
              {' • '}
              {r.cookMinutes ?? 0} min • {r.servings ?? 0} servings
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
