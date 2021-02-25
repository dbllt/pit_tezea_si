import React, {Component, createRef} from 'react';
import styles from './LoginScreen.module.css';
import {Button, TextField} from "@material-ui/core";
import {Redirect} from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import API from "../../network/API";


interface IProps {
    handler: (arg0: string) => void

}

interface IState {
    redirect: boolean
    triedToLogin: boolean
    displayError: boolean
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
    state = {
        redirect: false,
        triedToLogin: false,
        displayError:false
    }
    private readonly username: React.RefObject<any>;
    private readonly password: React.RefObject<any>;

    constructor(props: IProps) {
        super(props);
        this.username = createRef();
        this.password = createRef();

        this.getUsername = this.getUsername.bind(this);
        this.getPassword = this.getPassword.bind(this);
        this.login = this.login.bind(this);

        this.keyPress = this.keyPress.bind(this);
        this.DisplayError = this.DisplayError.bind(this);
    }

    login() {
        this.setState({triedToLogin: true})
        if (this.getUsername() !== "" && this.getPassword() !== "") {
            API.login(this.getUsername(), this.getPassword()).then((b) => {
                    if (b) {
                        this.props.handler(this.getUsername())
                    }else{
                        this.setState({displayError:true})
                    }
                }
            );
        }
    }

    DisplayError(){
        if(this.state.displayError){
            return <p style={{color:"red"}}>Erreur : Identifiant/Mot de Passe Invalide</p>
        }else{
            return <div/>
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

    keyPress(e: React.KeyboardEvent<HTMLInputElement>) {
        if (e.key === "Enter") {
            this.login();
        }
    }

    render() {
        return (
            <div className={styles.LoginScreen}>
                <RedirectionIfNotConnected/>
                <h1>Connexion</h1>
                <this.DisplayError/>
                <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
                    <Grid item>
                        <TextField
                            label="Identifiant"
                            inputRef={this.username}
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                            error={(this.state.triedToLogin && this.getUsername() === "")}
                            helperText={(this.state.triedToLogin && this.getUsername() === "") ? 'Manquant' : ' '}
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
                            error={(this.state.triedToLogin && this.getPassword() === "")}
                            helperText={(this.state.triedToLogin && this.getPassword() === "") ? 'Manquant' : ' '}
                            onKeyDown={this.keyPress}
                        />
                    </Grid>
                    <Grid item>
                        <Button type="button" onClick={this.login}
                                style={{backgroundColor: "#8fbe40", color: 'white', padding: 15}}>
                            Se connecter
                        </Button>
                    </Grid>
                </Grid>
            </div>
        )
    }
}

export default LoginScreen
