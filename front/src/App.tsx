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


interface IProps {
}
interface IState {
    username: string;
}

class App extends React.Component<IProps,IState> {
    constructor(props: ClassAttributes<any>) {
        super(props)
        this.state = {username: ""}

        this.handler = this.handler.bind(this)
        this.handler2 = this.handler2.bind(this)
    }

    handler() {
        this.setState({})
    }

    handler2(username: string) {
        this.setState({username})
    }

    render() {
        return (
            <div className="App">
                <HashRouter>
                    <div>
                        <CustomAppBar handler={this.handler} username={this.state.username}/>

                        <div className="content">
                            <Route
                                path='/login'
                                render={(props) => (
                                    <LoginScreen {...props} handler={this.handler2}/>
                                )}
                            />
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