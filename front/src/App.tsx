import React from 'react';
import './App.css';
import Menu from "./components/Menu/Menu";
import {HashRouter, Route} from "react-router-dom";
import RequestsList from "./components/RequestsList/RequestsList";
import NewRequest from "./components/NewRequest/NewRequest";

function App() {
  return (
      <div className="App">
          <HashRouter>
              <div>
                  <h1>TEZEA SI</h1>
                  <div className="content">
                      <Route exact path="/" component={Menu}/>
                      <Route path="/requestsList" component={RequestsList}/>
                      <Route path="/newRequest" component={NewRequest}/>
                  </div>
              </div>
          </HashRouter>
      </div>
  );
}

export default App;
