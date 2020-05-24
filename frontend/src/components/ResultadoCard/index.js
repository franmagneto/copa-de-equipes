import React from 'react';
import './styles.css';

function ResultadoCard({ posicao, nome }) {
  return (
    <div className="resultado-card-container">
      <span className="posicao">
        {posicao}º
      </span>
      <span className="nome">
        {nome}
      </span>
    </div>
  );
}

export default ResultadoCard;
