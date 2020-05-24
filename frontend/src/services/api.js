import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

export default {
  async getEquipes() {
    const { data } = await api.get('/equipes');
    return data;
  },

  async postSelecao(selecao) {
    const { data } = await api.post('/equipes/selecao', selecao);
    return data;
  }
};
