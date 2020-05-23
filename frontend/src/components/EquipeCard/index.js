import React from 'react';
import './styles.css';

function EquipeCard(props) {
  return (
    <div className="equipe-card-container">
      <input
        type="checkbox"
        checked={props.checked}
        onChange={props.onChange}
      />
      <ul>
        <li>{props.nome}</li>
        <li>{props.sigla}</li>
      </ul>
    </div>
  );
}

export default EquipeCard;
