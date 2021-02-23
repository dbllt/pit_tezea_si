import React, {Component} from 'react';
import './App.css';
import Menu from "./components/Menu/Menu";
import {HashRouter, Link, Route} from "react-router-dom";
import RequestsList from "./components/RequestsList/RequestsList";
import NewRequest from "./components/NewRequest/NewRequest";
import {AppBar, Button, Toolbar} from "@material-ui/core";
import ServiceList from "./components/ServiceList/ServiceList";
import LoginScreen from "./components/LoginScreen/LoginScreen";
import NewUserScreen from "./components/NewUserScreen/NewUserScreen";
import UsersScreen from "./components/UsersScreen/UsersScreen";
import API from "./network/API";
import logo from './assets/logo.png';


class App extends Component {

    disconnect = () => {
        API.disconnect().then(() => {
                this.forceUpdate();
            }
        );
    };


    render() {
        return (
            <div className="App">
                <HashRouter>
                    <div>
                        <AppBar position="static" style={{
                            background: '#8fbe40', minHeight: 80,
                            display: "flex",
                            justifyContent: "center",
                        }}>
                            <Toolbar>
                                <Link to="/" style={{
                                    float: "left"
                                }}>
                                    <img
                                        src={logo}
                                        alt=""
                                        width="70%"
                                        height="auto"
                                        style={{
                                            display: "block"
                                        }}
                                    />
                                </Link>
                                <Button onClick={this.disconnect} className={"toolbarButtons"}>
                                    Déconnexion
                                </Button>
                            </Toolbar>
                        </AppBar>

                        <div className="content">
                            <Route path="/login" component={LoginScreen}/>
                            <Route path="/requestsList" component={RequestsList}/>
                            <Route path="/newRequest" component={NewRequest}/>
                            <Route path="/serviceList" component={ServiceList}/>
                            <Route exact path="/" component={Menu}/>
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
