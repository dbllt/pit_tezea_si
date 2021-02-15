import React, {Component} from 'react';
import './App.css';
import Menu from "./components/Menu/Menu";
import {HashRouter, Link, Route} from "react-router-dom";
import RequestsList from "./components/RequestsList/RequestsList";
import NewRequest from "./components/NewRequest/NewRequest";
import {AppBar, Toolbar} from "@material-ui/core";
import ServiceList from "./components/ServiceList/ServiceList";
import LoginScreen from "./components/LoginScreen/LoginScreen";
import NewUserScreen from "./components/NewUserScreen/NewUserScreen";
import UsersScreen from "./components/UsersScreen/UsersScreen";


class App extends Component {
    render() {
        return (
            <div className="App">
                <HashRouter>
                    <div>
                        <AppBar position="static" style={{background: '#8fbe40'}}>
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
                            <Route path="/menu" component={Menu}/>
                            <Route path="/requestsList" component={RequestsList}/>
                            <Route path="/newRequest" component={NewRequest}/>
                            <Route path="/serviceList" component={ServiceList}/>
                            <Route exact path="/" component={LoginScreen}/>
                            <Route path="/addUser" component={NewUserScreen}/>
                            <Route path="/users" component={UsersScreen}/>
                        </div>
                    </div>
                </HashRouter>
            </div>
        );
    }
}

export default App;
