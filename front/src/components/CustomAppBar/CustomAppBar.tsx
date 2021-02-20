import React from 'react';
import {Link, Route, RouteComponentProps} from "react-router-dom";
import {AppBar, Button, Toolbar} from "@material-ui/core";
import API from "../../network/API";
import logo from '../../assets/logo.png';
import {withRouter} from 'react-router';

interface IState {
}

interface IProps{
    handler: () => void

}

type IndexProps = RouteComponentProps<{}, {}, {}>;


class CustomAppBar extends React.Component<RouteComponentProps & IProps, any> {

    disconnect = () => {
        API.disconnect().then(() => {
                this.forceUpdate();
                this.props.handler();
            }
        );
    };


    DisplayDisconnect() {
        if (this.props.location.pathname !== "/login") {
            return <Button onClick={this.disconnect} className={"toolbarButtons"}>
                Déconnexion
            </Button>
        }
    }

    render() {
        return (
            <div className="App">
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
                        {this.DisplayDisconnect()}
                    </Toolbar>
                </AppBar>

            </div>
        );
    }
}


export default withRouter(CustomAppBar);