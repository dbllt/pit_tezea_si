import React from 'react';
import styles from './RegisterScreen.module.css';
import {Button, TextField} from "@material-ui/core";
import {Link} from "react-router-dom";
import Grid from "@material-ui/core/Grid";

const RegisterScreen: React.FC = () => (
    <div className={styles.LoginScreen}>

        <Grid container direction="column" justify="center" alignItems="center" spacing={5}>
            <h1>Inscription</h1>
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
                        S'inscrire
                    </Button>
                </Link>
            </Grid>
            <Grid>
                <Link to="/">
                    <Button type="button">
                        Retour
                    </Button>
                </Link>
            </Grid>
        </Grid>
    </div>
);

export default RegisterScreen;
