import React from 'react';
import './App.css';
import Menu from "./components/Menu/Menu";
import {HashRouter, Link, Route} from "react-router-dom";
import ListeDemandes from "./components/ListeDemandes/ListeDemandes";
import NouvelleDemande from "./components/NouvelleDemande/NouvelleDemande";

function App() {
  return (
      <div className="App">
          <HashRouter>
              <div>
                  <h1>TEZEA SI</h1>
                  <div className="content">
                      <Route exact path="/" component={Menu}/>
                      <Route path="/liste" component={ListeDemandes}/>
                      <Route path="/nouvelle_demande" component={NouvelleDemande}/>
                  </div>
              </div>
          </HashRouter>
      </div>
  );
}

export default App;
