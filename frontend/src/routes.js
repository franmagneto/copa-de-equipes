import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import SelecaoScreen from './screens/SelecaoScreen';
import ResultadoScreen from './screens/ResultadoScreen';

function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={SelecaoScreen} />
        <Route exact path="/resultado" component={ResultadoScreen} />
      </Switch>
    </BrowserRouter>
  );
}

export default Routes;
