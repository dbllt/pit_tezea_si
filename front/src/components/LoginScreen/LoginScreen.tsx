import React, {Component, createRef} from 'react';
import styles from './LoginScreen.module.css';
import {Button, TextField} from "@material-ui/core";
import {Redirect} from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import API from "../../network/API";


interface IProps {
}

interface IState {
    redirect: boolean
}

function RedirectionIfNotConnected() {
    let temp = localStorage.getItem('token');
    if (temp === null) {
        temp = "";
    }
    let token: string = temp;
    if (token !== "") {
        return <Redirect to="/"/>
    } else {
        return <div/>
    }
}

class LoginScreen extends Component<IProps, IState> {
    private username: React.RefObject<any>;
    private password: React.RefObject<any>;

    state = {
        redirect: false,
    }

    constructor(props: IProps) {
        super(props);
        this.username = createRef();
        this.password = createRef();

        this.getUsername = this.getUsername.bind(this);
        this.getPassword = this.getPassword.bind(this);
        this.login = this.login.bind(this);
    }

    login() {
        if (this.getUsername() !== "" && this.getPassword() !== "") {
            API.login(this.getUsername(), this.getPassword()).then((b) => {
                    if (b) {
                        this.setState({redirect: true})
                    }
                }
            );
        }
    }

    getPassword(): string {
        if (this.password.current == null) {
            return "";
        } else {
            return this.password.current.value;
        }
    };

    getUsername(): string {
        if (this.username.current == null) {
            return "";
        } else {
            return this.username.current.value;
        }
    };


    render() {
        return (
            <div className={styles.LoginScreen}>
                <RedirectionIfNotConnected/>
                <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
                    <h1>Connexion</h1>
                    <Grid item>
                        <TextField
                            label="Identifiant:"
                            inputRef={this.username}
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                        />
                    </Grid>
                    <Grid item>
                        <TextField
                            label="Mot de passe"
                            inputRef={this.password}
                            type="password"
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                        />
                    </Grid>
                    <Grid>
                        <Button type="button" onClick={this.login}>
                            Se connecter
                        </Button>
                    </Grid>
                </Grid>
                {this.state.redirect ? (<Redirect push to="/"/>) : null}
            </div>
        )
    }
}

export default LoginScreen
