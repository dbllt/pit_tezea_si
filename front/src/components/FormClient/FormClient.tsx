import React, {Component} from 'react';
import { TextField} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import styles from './FormClient.module.css';
import PhoneInput from 'react-phone-input-2'
import 'react-phone-input-2/lib/style.css'
import FormControl from "@material-ui/core/FormControl";


interface IClient {
    clientStatus: string,
    company: string,
    siret: string,
    gender: string,
    lName: string,
    fName: string,
    phone: string,
    email: string,
    address: string,
    cp: string,
    city: string
}

interface Props {
    parentCallback:Function,
    client:IClient
}

interface State {
    statut: string;
    gender: string;
    nom: string;
    siret: string;
    lastName: string;
    firstName: string;
    tel: string;
    email: string;
    address: string;
    codepostal: string;
    ville: string;
}


class FormClient extends Component<Props, State> {

    constructor(props: Props) {
        super(props);
        const client = this.props.client;
        console.log("form client", client)

        this.state = {
            statut: client.clientStatus,
            gender: client.gender,
            nom: client.company,
            siret: client.siret,
            lastName: client.lName,
            firstName: client.fName,
            tel: client.phone,
            email: client.email,
            address: client.address,
            codepostal: client.cp,
            ville: client.city
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    // componentDidMount() {
    //     const client = this.props.client;
    //     console.log(this.props)
    //     this.setState({
    //         statut: client.clientStatus,
    //         gender: client.gender,
    //         nom: client.company,
    //         siret: client.siret,
    //         lastName: client.lName,
    //         firstName: client.fName,
    //         tel: client.phone,
    //         email: client.email,
    //         address: client.address,
    //         codepostal: client.cp,
    //         ville: client.city
    //     });
    // }

    componentDidUpdate(prevProps: { client: any; }) {
        if(prevProps.client !== this.props.client) {
            const client = this.props.client;
            this.setState({
                statut: client.clientStatus,
                gender: client.gender,
                nom: client.company,
                siret: client.siret,
                lastName: client.lName,
                firstName: client.fName,
                tel: client.phone,
                email: client.email,
                address: client.address,
                codepostal: client.cp,
                ville: client.city
            });
        }
    }

    sendData(){
        var client:IClient;
        if(this.state.statut === "Particulier"){
            client = {
                clientStatus: this.state.statut,
                company: "",
                siret: "",
                gender: this.state.gender,
                lName: this.state.lastName,
                fName: this.state.firstName,
                phone: this.state.tel,
                email: this.state.email,
                address: this.state.address,
                cp: this.state.codepostal,
                city: this.state.ville
            }
        }else {
            client = {
                clientStatus: this.state.statut,
                company: this.state.nom,
                siret: this.state.siret,
                gender: "",
                lName: "",
                fName: "",
                phone: this.state.tel,
                email: this.state.email,
                address: this.state.address,
                cp: this.state.codepostal,
                city: this.state.ville
            }
        }
        this.props.parentCallback(client);
   }


    handleChange(event: any) {
        const {target: {name, value}} = event;
        const newState = {[name]: value} as Pick<State, keyof State>;
        this.setState(newState,this.handleSubmit);
    }

    handleSubmit(){
        this.sendData();
    }


    render() {
        return (
            <form>
                <Grid container direction="column" alignItems="flex-start" justify="flex-start">
                    
                    <Grid item>
                        <h3>Informations client :</h3>
                    </Grid>

                    <Grid container className={styles.Gridlabelfield}>
                        <Grid item className={styles.Label}>
                            Statut client : 
                        </Grid>
                        <Grid item>
                            <FormControl>
                            <Select placeholder="Statut" name="statut"
                                    value={this.state.statut} onChange={this.handleChange} required>
                                <MenuItem value="Particulier">Particulier</MenuItem>
                                <MenuItem value="Entreprise">Entreprise</MenuItem>
                                <MenuItem value="Collectivité">Collectivité</MenuItem>
                                <MenuItem value="Association">Association</MenuItem>
                            </Select>
                            </FormControl>
                        </Grid>
                    </Grid>


                    {this.state.statut === 'Particulier'
                        ? <Grid container className={styles.Gridlabelfield}>
                            <Grid item className={styles.Label}>
                                Civilité :
                            </Grid>
                            <Grid item>
                                <FormControl>
                                    <Select
                                        name="gender"
                                        value={this.state.gender} onChange={this.handleChange} required
                                    >
                                        <MenuItem value="M">M</MenuItem>
                                        <MenuItem value="Mme">Mme</MenuItem>
                                    </Select>
                                </FormControl>
                            </Grid>
                        </Grid>
                        : <div><Grid container className={styles.Gridlabelfield}>
                            <Grid item className={styles.Label}>
                                Nom :
                            </Grid>
                            <Grid item>
                                <TextField placeholder="Nom" name="nom"
                                            value={this.state.nom} onChange={this.handleChange} required/>
                            </Grid>
                        </Grid>
                        <Grid container className={styles.Gridlabelfield}>
                            <Grid item className={styles.Label}>
                                SIRET :
                            </Grid>
                            <Grid item>
                                <TextField placeholder="SIRET" name="siret"
                                            value={this.state.siret} onChange={this.handleChange} required/>
                            </Grid>
                        </Grid></div>
                    }

                    <Grid container> {/* grill row */}
                        <Grid item xs={6}> {/* 1ère Grille gauche */}
                            {this.state.statut === 'Particulier' &&
                            <Grid container className={styles.Gridlabelfield}>
                                <Grid item className={styles.Label}>
                                    Nom :
                                </Grid>
                                <Grid item>
                                    <TextField placeholder="Nom" name="lastName"
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
                                    <TextField placeholder="Adresse"
                                                name="address" value={this.state.address}
                                                onChange={this.handleChange} required/>
                                </Grid>
                            </Grid>

                            <Grid container className={styles.Gridlabelfield}>
                                <Grid item className={styles.Label}>
                                    Ville :
                                </Grid>
                                <Grid item>
                                    <TextField  placeholder="Ville" name="ville"
                                                value={this.state.ville} onChange={this.handleChange} required/>
                                </Grid>
                            </Grid>
                        </Grid>

                        <Grid item xs={6}> {/* 2éme Grille droite */}
                            {(this.state.statut === 'Particulier') &&
                             <Grid container className={styles.Gridlabelfield}>

                                <Grid item className={styles.Label}>
                                    Prénom :
                                </Grid>
                                <Grid item>
                                    <TextField placeholder="Prénom" name="firstName"
                                                value={this.state.firstName} onChange={this.handleChange} required/>
                                </Grid>
                             </Grid>
                            
                            }

                            <Grid container className={styles.Gridlabelfield}>
                                <Grid item className={styles.Label}>
                                    Email :
                                </Grid>
                                <Grid item>
                                    <TextField  placeholder="Email" name="email"
                                                value={this.state.email} onChange={this.handleChange} required/>
                                </Grid>
                            </Grid>
                            <Grid container className={styles.Gridlabelfield}>
                                <Grid item className={styles.Label}>
                                    Code postal :
                                </Grid>
                                <Grid item>
                                    <TextField placeholder="Code postal" name="codepostal"
                                                value={this.state.codepostal} onChange={this.handleChange} required/>
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
            </form> 
        );
    }

}

export default FormClient
