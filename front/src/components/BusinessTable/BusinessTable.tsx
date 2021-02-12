import React, { Component } from 'react';
import { createStyles, withStyles, WithStyles } from "@material-ui/core/styles";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';




function createData(name: string, calories: string, fat: string, carbs: number, protein: number) {
    return { name, calories, fat, carbs, protein };
}


function createRow(nb: string, date: string, heure: string, concierge: string, prestation_don: string, statut_client: string, entreprise: string, civilité: string, nom_contact: string, prenom_contact: string, telephone: string, e_mail: string, adresse: string, code_postal: string, ville : string, urgence: string, statut_demande : string, affectation_de_la_demande: string, rv_estimation_pris_commercial: string, rv_confirme_par_client_livraison_conciergerie_ou_commercial: string, positionné_planning: string) {
    return {};
}

const rows = [
    // { "1", "2018-01-25", "10:30", "Jouadé", "Prestation", "Particulier", "", "Mr", "", "", "", "", "", "35550", "PIPRIAC", "Haute", "Cloturée", "Gwen", "???""," ??? "", "???", "Conciergerie", "Taille Haie de sapins à Bruc/aff (HT)", "JOUADE ANTOINE TOUJOURS PAS FAIT DONC AUTRES PRESTATAIRES DONNE: JOUIN ET ARTHUR ELAGAGE", "", "Taille haie Tezea ne fait pas"}
];

const tableHeadName = ["N°", "Date", "Heure", "Concierge", "Prestation/Don", "Statut Client", "ENTREPRISE", "Civilité", "NOM contact", "PRENOM Contact", "Telephone", "E-mail", "Adresse", "Code postal", "Ville", "Urgence (0-5)", "Statut Demande", "Affectation de la demande", "RV Estimation pris(Commercial )", "RV Confirmé  par Client Livraison(Conciergerie ou Commercial)", "Positionné Planning(Conciergerie ou Commercial)", "Cloturé par", "Détail de la demande", "Nom prestataire", "Niveau satisfaction", "Notes supplémentaires"];

const styles = () => createStyles({
    table: {
        maxWidth: 650,
    },
});

interface Props extends WithStyles<typeof styles> { }

class BusinessTable extends Component<Props> {


    render() {
        const { classes } = this.props;

        return (
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            {tableHeadName.map((name) => (<TableCell>{name}</TableCell>))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {/* {rows.map((row) => (
                            <TableRow key={row.name}>
                                <TableCell component="th" scope="row">
                                    {row.name}
                                </TableCell>
                                <TableCell align="right">{row.calories}</TableCell>
                                <TableCell align="right">{row.fat}</TableCell>
                                <TableCell align="right">{row.carbs}</TableCell>
                                <TableCell align="right">{row.protein}</TableCell>
                            </TableRow>
                        ))} */}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }
}

export default withStyles(styles, { withTheme: true })(BusinessTable);