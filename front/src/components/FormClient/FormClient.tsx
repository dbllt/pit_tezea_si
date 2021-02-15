import React, { ChangeEvent, Component } from 'react';
import {Container, TextField} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import styles from './FormClient.module.css';
import PhoneInput from 'react-phone-input-2'
import 'react-phone-input-2/lib/style.css'

interface Props {

};

interface State {
    statut : string
};

class FormClient extends Component<Props,State> {

    constructor(props: Props) {
        super(props);
        this.state = {statut : "P"};

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event:any) {
        this.setState({statut: event.target.value});
    }

    render(){
        return (
            <Container>
                <form>
                    <Grid container direction="column" alignItems="flex-start">

                        <Grid item>
                            <h3>Informations client :</h3>
                        </Grid>

                        <Grid container className={styles.Gridlabelfield}>
                            <Grid item className={styles.Label}>
                                Statut client :
                            </Grid>
                            <Grid item>
                                <Select placeholder="Statut" onChange={this.handleChange} required>
                                    <MenuItem value="P">Particulier</MenuItem>
                                    <MenuItem value="E">Entreprise</MenuItem>
                                    <MenuItem value="C">Collectivité</MenuItem>
                                    <MenuItem value="A">Association</MenuItem>
                                </Select>
                            </Grid>
                        </Grid>

                        { this.state.statut == 'P'
                            ?<Grid container className={styles.Gridlabelfield} >
                                <Grid item className={styles.Label}>
                                    Civilité :
                                </Grid>
                                <Grid item >
                                    <Select required>
                                        <MenuItem value="M">Monsieur</MenuItem>
                                        <MenuItem value="F">Madame</MenuItem>
                                    </Select>
                                </Grid>
                            </Grid>
                            : <Grid container className={styles.Gridlabelfield}>
                                <Grid item className={styles.Label}>
                                    Nom :
                                </Grid>
                                <Grid item>
                                    <TextField id="standard-basic" placeholder="Nom" required />
                                </Grid>
                            </Grid>
                        }

                        <Grid container> {/* grill row */}
                            <Grid item xs={6}> {/* 1ère Grille gauche */}
                                { this.state.statut == 'P'  &&
                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Nom :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Nom" required />
                                    </Grid>
                                </Grid>
                                }
                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Téléphone :
                                    </Grid>
                                    <Grid item>
                                        <PhoneInput country={'fr'} />
                                    </Grid>
                                </Grid>

                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Adresse :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Adresse" required />
                                    </Grid>
                                </Grid>

                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Ville :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Ville" required />
                                    </Grid>
                                </Grid>
                            </Grid>

                            <Grid item xs={6}> {/* 2éme Grille droite */}
                                { this.state.statut == 'P' &&
                                <Grid container className={styles.Gridlabelfield}>

                                    <Grid item className={styles.Label}>
                                        Prénom :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Prénom" required />
                                    </Grid>
                                </Grid>
                                }

                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Email :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Email" required />
                                    </Grid>
                                </Grid>
                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Code postale :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Code postale" required />
                                    </Grid>
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                </form>
            </Container>
        );
    }

}

export default FormClient
