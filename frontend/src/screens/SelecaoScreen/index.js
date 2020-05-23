import React, { useState, useEffect } from 'react';
import './styles.css';

import Header from '../../components/Header';
import EquipeCard from '../../components/EquipeCard';

import api from '../../services/api';

function SelecaoScreen() {
  const [equipes, setEquipes] = useState([]);

  useEffect(() => {
    api.getEquipes()
      .then(setEquipes);
  }, []);

  return (
    <div className="selecao-container">
      <Header title="Fase de Seleção">
        Selecione 8 equipes que você deseja para a competição e depois
        pressione o botão GERAR COPA para prosseguir.
      </Header>
      <div className="selecao-info">
        <div>
          <h2>Selecionados</h2>
          <span className="selecionados">0 de 8 equipes</span>
        </div>
        <button>Gerar Copa</button>
      </div>
      <div className="selecao">
        {equipes.map(equipe => (
          <EquipeCard
            nome={equipe.nome}
            sigla={equipe.sigla}
            key={equipe.id}
          />
        ))}
      </div>
    </div>
  );
}

export default SelecaoScreen;
