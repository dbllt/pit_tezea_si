import React, {ClassAttributes} from 'react';
import './App.css';
import Menu from "./components/Menu/Menu";
import {HashRouter, Route} from "react-router-dom";
import RequestsList from "./components/RequestsList/RequestsList";
import Request from "./components/Request/Request";
import ServiceList from "./components/ServiceList/ServiceList";
import LoginScreen from "./components/LoginScreen/LoginScreen";
import NewUserScreen from "./components/NewUserScreen/NewUserScreen";
import UsersScreen from "./components/UsersScreen/UsersScreen";
import CustomAppBar from "./components/CustomAppBar/CustomAppBar";


class App extends React.Component {
    constructor(props: ClassAttributes<any>) {
        super(props)

        this.handler = this.handler.bind(this)
    }

    handler() {
        this.setState({})
    }

    render() {
        return (
            <div className="App">
                <HashRouter>
                    <div>
                        <CustomAppBar handler={this.handler}/>

                        <div className="content">
                            <Route path="/login" component={LoginScreen}/>
                            <Route path="/requestsList" component={RequestsList}/>
                            <Route path="/request" component={Request}/>
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