import React from 'react';
import './styles.css';

function EquipeCard({ nome, sigla }) {
  return (
    <div className="equipe-card-container">
      <input type="checkbox" />
      <ul>
        <li>{nome}</li>
        <li>{sigla}</li>
      </ul>
    </div>
  );
}

export default EquipeCard;
