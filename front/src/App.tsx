import React from 'react';
import './App.css';
import Menu from "./components/Menu/Menu";
import {HashRouter, Link, Route} from "react-router-dom";
import RequestsList from "./components/RequestsList/RequestsList";
import NewRequest from "./components/NewRequest/NewRequest";
import {AppBar, Toolbar} from "@material-ui/core";

function App() {
  return (
      <div className="App">
          <HashRouter>
              <div>
                  <AppBar position="static" style={{ background: '#8fbe40' }}>
                      <Toolbar>
                          <Link to="/">
                              <img
                                  src="https://tezea.fr/wp-content/uploads/2018/02/Logo-Tezea-Horizontal-sur-aplat-blanc-150-170x95-3997.png"
                                  alt=""
                                  width="auto"
                                  height="50"
                              />
                          </Link>
                      </Toolbar>
                  </AppBar>

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
