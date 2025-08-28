import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import api from '../services/api';

export default function MyRecipeDetail() {
  const { id } = useParams();
  const [recipe, setRecipe] = useState(null);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState('');

  useEffect(() => {
    let cancelled = false;
    (async () => {
      setLoading(true); setErr('');
      try {
        const res = await api.get(`/recipes/${id}`, { validateStatus: () => true });
        if (cancelled) return;
        if (res.status === 200) setRecipe(res.data);
        else if (res.status === 404) setErr('Recipe not found.');
        else setErr(`Load failed (${res.status}).`);
      } catch (e) {
        if (!cancelled) setErr('Network error.');
        console.error(e);
      } finally {
        if (!cancelled) setLoading(false);
      }
    })();
    return () => { cancelled = true; };
  }, [id]);

  if (loading) {
    return <p style={{ maxWidth: 900, margin: '24px auto' }}>Loading…</p>;
  }

  if (err || !recipe) {
    return (
      <div style={{ maxWidth: 900, margin: '24px auto' }}>
        <Link to="/my-recipes">← Back</Link>
        <p style={{ color: 'crimson', marginTop: 8 }}>{err || 'Not found.'}</p>
      </div>
    );
  }

  return (
    <div style={{ maxWidth: 900, margin: '24px auto' }}>
      <div style={{ display:'flex', gap:12, alignItems:'center' }}>
        <Link to="/my-recipes">← Back</Link>
        {/* 👇 Edit link near the top */}
        <Link to={`/my-recipes/${id}/edit`}>Edit</Link>
      </div>

      <h2 style={{ margin:'8px 0 12px' }}>{recipe.title}</h2>

      {recipe.imageUrl && (
        <img
          src={recipe.imageUrl}
          alt={recipe.title || 'Recipe image'}
          style={{ maxWidth:'100%', borderRadius:6, marginBottom:12 }}
        />
      )}

      <p>
        <b>Cook:</b> {recipe.cookMinutes ?? 0} min • <b>Servings:</b> {recipe.servings ?? 0}
      </p>

      <h3>Ingredients</h3>
      {(recipe.ingredients && recipe.ingredients.length > 0) ? (
        <ul>
          {recipe.ingredients.map((ri, i) => (
            <li key={i}>
              {ri.quantity ? `${ri.quantity} ` : ''}
              {ri.unit} {ri.name}
            </li>
          ))}
        </ul>
      ) : (
        <p>No ingredients listed.</p>
      )}

      <h3>Steps</h3>
      {(recipe.steps && recipe.steps.length > 0) ? (
        <ol>
          {recipe.steps.map((s, i) => <li key={i}>{s}</li>)}
        </ol>
      ) : (
        <p>No steps listed.</p>
      )}
    </div>
  );
}
