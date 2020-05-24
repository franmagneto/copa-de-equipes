import React, { useState, useEffect } from 'react';
import { Redirect } from 'react-router-dom';
import './styles.css';

import Header from '../../components/Header';
import EquipeCard from '../../components/EquipeCard';

import api from '../../services/api';

function SelecaoScreen() {
  const [equipes, setEquipes] = useState([]);
  const [selecionadas, setSelecionadas] = useState(0);
  const [resultado, setResultado] = useState([]);

  useEffect(() => {
    api.getEquipes()
      .then(setEquipes);
  }, []);

  function onSelectEquipe(equipeIndex) {
    return ({ target }) => {
      if (target.checked && selecionadas >= 8) return;

      const newEquipes = [...equipes];
      newEquipes[equipeIndex].checked = target.checked;

      setEquipes(newEquipes);
      setSelecionadas(selecionadas + (target.checked ? 1 : -1));
    };
  }

  async function gerarCopa() {
    if (selecionadas !== 8) {
      return alert('Selecione 8 equipes!');
    }

    const selecao = equipes
      .filter(equipe => equipe.checked)
      .map(equipe => equipe.id);

    const resultado = await api.postSelecao(selecao);
    setResultado(resultado)
  }

  return (resultado.length > 0) ? (
    <Redirect
      push
      to={{ pathname: '/resultado', state: { resultado } }}
    />
  ) : (
    <div className="selecao-container">
      <Header title="Fase de Seleção">
        Selecione 8 equipes que você deseja para a competição e depois
        pressione o botão GERAR COPA para prosseguir.
      </Header>
      <div className="selecao-info">
        <div>
          <h2>Selecionados</h2>
          <span className="selecionados">
            {selecionadas} de 8 equipes
          </span>
        </div>
        <button onClick={gerarCopa}>Gerar Copa</button>
      </div>
      <div className="selecao">
        {equipes.map((equipe, index) => (
          <EquipeCard
            nome={equipe.nome}
            sigla={equipe.sigla}
            key={equipe.id}
            checked={!!equipe.checked}
            onChange={onSelectEquipe(index)}
          />
        ))}
      </div>
    </div>
  );
}

export default SelecaoScreen;
