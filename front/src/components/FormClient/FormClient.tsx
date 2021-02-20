import React, {Component} from 'react';
import {Container, TextField} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import styles from './FormClient.module.css';
import PhoneInput from 'react-phone-input-2'
import 'react-phone-input-2/lib/style.css'

interface Props {

}

interface State {
    statut: String;
    gender: String;
    nom: String;
    lastName: String;
    firstName: String;
    tel: string;
    email: String;
    address: String;
    codepostal: String;
    ville: String;
}


class FormClient extends Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.state = {
            statut: "P",
            gender: "",
            nom: "",
            lastName: "",
            firstName: "",
            tel: "",
            email: "",
            address: "",
            codepostal: "",
            ville: ""
        };

        this.handleChange = this.handleChange.bind(this);

    }


    handleChange(event: any) {
        const {target: {name, value}} = event;
        const newState = {[name]: value} as Pick<State, keyof State>;
        this.setState(newState);
    }

    render() {
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
                                <Select placeholder="Statut" name="statut"
                                        value={this.state.statut} onChange={this.handleChange} required>
                                    <MenuItem value="P">Particulier</MenuItem>
                                    <MenuItem value="E">Entreprise</MenuItem>
                                    <MenuItem value="C">Collectivité</MenuItem>
                                    <MenuItem value="A">Association</MenuItem>
                                </Select>
                            </Grid>
                        </Grid>

                        {this.state.statut === 'P'
                            ? <Grid container className={styles.Gridlabelfield}>
                                <Grid item className={styles.Label}>
                                    Civilité :
                                </Grid>
                                <Grid item>
                                    <Select name="gender" value={this.state.gender}
                                            onChange={this.handleChange} required>
                                        <MenuItem value="Monsieur">Monsieur</MenuItem>
                                        <MenuItem value="Madame">Madame</MenuItem>
                                    </Select>
                                </Grid>
                            </Grid>
                            : <Grid container className={styles.Gridlabelfield}>
                                <Grid item className={styles.Label}>
                                    Nom :
                                </Grid>
                                <Grid item>
                                    <TextField id="standard-basic" placeholder="Nom" name="nom"
                                               value={this.state.nom} onChange={this.handleChange} required/>
                                </Grid>
                            </Grid>
                        }

                        <Grid container> {/* grill row */}
                            <Grid item xs={6}> {/* 1ère Grille gauche */}
                                {this.state.statut === 'P' &&
                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Nom :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Nom" name="lastName"
                                                   value={this.state.lastName} onChange={this.handleChange} required/>
                                    </Grid>
                                </Grid>
                                }
                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Téléphone :
                                    </Grid>
                                    <Grid item>
                                        <PhoneInput country={'fr'} value={this.state.tel}
                                                    onChange={phone => this.setState({tel: "+" + phone})}/>
                                    </Grid>
                                </Grid>

                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Adresse :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Adresse"
                                                   name="address" value={this.state.address}
                                                   onChange={this.handleChange} required/>
                                    </Grid>
                                </Grid>

                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Ville :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Ville" name="ville"
                                                   value={this.state.ville} onChange={this.handleChange} required/>
                                    </Grid>
                                </Grid>
                            </Grid>

                            <Grid item xs={6}> {/* 2éme Grille droite */}
                                {this.state.statut === 'P' &&
                                <Grid container className={styles.Gridlabelfield}>

                                    <Grid item className={styles.Label}>
                                        Prénom :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Prénom" name="firstName"
                                                   value={this.state.firstName} onChange={this.handleChange} required/>
                                    </Grid>
                                </Grid>
                                }

                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Email :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Email" name="email"
                                                   value={this.state.email} onChange={this.handleChange} required/>
                                    </Grid>
                                </Grid>
                                <Grid container className={styles.Gridlabelfield}>
                                    <Grid item className={styles.Label}>
                                        Code postal :
                                    </Grid>
                                    <Grid item>
                                        <TextField id="standard-basic" placeholder="Code postal" name="codepostal"
                                                   value={this.state.codepostal} onChange={this.handleChange} required/>
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
