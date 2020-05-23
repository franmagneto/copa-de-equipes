import React from 'react';
import './styles.css';

import Header from '../../components/Header';
import EquipeCard from '../../components/EquipeCard';

function SelecaoScreen() {
  const equipes = [
    {
      id: 0,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 1,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 2,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 3,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 4,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 5,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 6,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 7,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 8,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 9,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 10,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 11,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 12,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 13,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 14,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
    {
      id: 15,
      nome: 'Nome Equipe',
      sigla: 'SiglaEquipe',
    },
  ];
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
