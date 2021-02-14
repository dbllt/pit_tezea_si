import React, {Component} from 'react';
import styles from './LoginScreen.module.css';
import {Button, TextField} from "@material-ui/core";
import {Link} from "react-router-dom";
import Grid from "@material-ui/core/Grid";

class LoginScreen extends Component {

    render() {
        return (
            <div className={styles.LoginScreen}>
        <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
            <h1>Connexion</h1>
            <Grid item>
                <TextField
                    label="Identifiant:"
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
                <Link to="/menu">
                    <Button type="button">
                        Se connecter
                    </Button>
                </Link>
            </Grid>
            <Grid>
                <Link to="/register">
                    <Button type="button">
                        S'inscrire
                    </Button>
                </Link>
                <Button type="button" onClick={async ()=>{await fetch("/api/ints")
            .then(res => res.json()).then((data) => {
                console.log(data)
            }).catch(console.log);}}>
                        Appel api
                    </Button>
            </Grid>
        </Grid>
    </div>
        )
    }
}

export default LoginScreen
