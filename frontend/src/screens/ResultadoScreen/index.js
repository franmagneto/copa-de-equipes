import React from 'react';
import './styles.css';

import Header from '../../components/Header';
import ResultadoCard from '../../components/ResultadoCard';

function ResultadoScreen({ location }) {
  const resultado = location.state?.resultado || [];

  return (
    <div className="resultado-container">
      <Header title="Resultado Final">
        Veja o resultado final da Copa de forma simples e rápida
      </Header>
      <div className="resultado-content">
        {resultado.map((equipe, index) => (
          <ResultadoCard
            nome={equipe.nome}
            key={equipe.id}
            posicao={index + 1}
          />
        ))}
      </div>
    </div>
  );
}

export default ResultadoScreen;
