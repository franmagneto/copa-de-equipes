import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import SelecaoScreen from './screens/SelecaoScreen';

function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={SelecaoScreen} />
      </Switch>
    </BrowserRouter>
  );
}

export default Routes;
