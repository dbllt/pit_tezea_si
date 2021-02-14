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

class LoginScreen extends Component<IProps, IState> {
    private identifiant: React.RefObject<any>;

    state = {
        redirect: false,
    }

    constructor(props: IProps) {
        super(props);
        this.identifiant = createRef();


        this.getIdentifiant = this.getIdentifiant.bind(this);
        this.login = this.login.bind(this);
    }

    login() {
        if (this.getIdentifiant() !== "") {
            API.login(this.getIdentifiant(), "").then(() => this.setState({redirect: true}));
        }
    }


    getIdentifiant(): string {
        if (this.identifiant.current == null) {
            return "";
        } else {
            return this.identifiant.current.value;
        }
    };


    render() {
        return (
            <div className={styles.LoginScreen}>
        <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
            <h1>Connexion</h1>
            <Grid item>
                <TextField
                    label="Identifiant:"
                    inputRef={this.identifiant}
                    id="outlined-margin-normal"
                    margin="normal"
                    variant="outlined"
                />
            </Grid>
            <Grid item>
                <TextField
                    label="Mot de passe"
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
                {this.state.redirect ? (<Redirect push to="/menu"/>) : null}
    </div>
        )
    }
}

export default LoginScreen
