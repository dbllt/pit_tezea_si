import React from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {Box, Collapse, IconButton, Typography} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import KeyboardArrowDownIcon from "@material-ui/icons/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@material-ui/icons/KeyboardArrowUp";

const useRowStyles = makeStyles({
    root: {
        "& > *": {
            borderBottom: '1px solid black',
        }
    }
});

const tableHeadNames = ["N° Demande", "Date", "Heure", "Concierge", 'Site', "Prestation/Don", 'Statut Demande', "Affectation demande", 'Urgence'];
const tableClientHeadNames = ["Statut Client", "Entreprise", "Civilité", "Nom", "Prénom", "Téléphone", "Email", "Adresse", "Code postal", "Ville"];
const rows = [
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes"),
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes"),
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes"),
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes"),
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes"),
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes"),
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes"),
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes"),
    createRequestData(1, "2018-01-25", "10:30", "Jouadé", "Menuiserie","Don", "En cours", "Ouvrier 3", "Urgent", "Particulier", "---", "M.", "Nom", "Prénom", 353535550, "email@email", "Rue rue", 55555, "Rennes")
]

function createRequestData(requestNumber: number, date: string, hour: string, concierge: string, site: string, serviceType: string, requestStatus: string, requestAssignment: string, emergency: string,
                           clientStatus: string, company: string, gender: string, lName: string, fName: string, phone: number, email: string, address: string, cp: number, city: string) {
    return {
        requestNumber, date, hour, concierge, site, serviceType, requestStatus, requestAssignment, emergency,
        clientStatus, company, gender, lName, fName, phone, email, address, cp, city
    };
}

function Row(props: { row: ReturnType<typeof createRequestData> }) {
    const { row } = props;
    const [open, setOpen] = React.useState(false);
    const classes = useRowStyles();

    return (
        <React.Fragment>
            <TableRow hover>
                <TableCell>
                    <IconButton
                        aria-label="expand row"
                        size="small"
                        onClick={() => setOpen(!open)}
                    >
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </TableCell>
                <TableCell align="left">{row.requestNumber}</TableCell>
                <TableCell align="left">{row.date}</TableCell>
                <TableCell align="left">{row.hour}</TableCell>
                <TableCell align="left">{row.concierge}</TableCell>
                <TableCell align="left">{row.site}</TableCell>
                <TableCell align="left">{row.serviceType}</TableCell>
                <TableCell align="left">{row.requestStatus}</TableCell>
                <TableCell align="left">{row.requestAssignment}</TableCell>
                <TableCell align="left">{row.emergency}</TableCell>
            </TableRow>
            <TableRow className={classes.root}>
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={10}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box margin={1}>
                            <Typography variant="h6" gutterBottom component="div">
                                Contact client <button>ICI Bouton: Détails demande (ouvre popup ou page detaillée de la demande ??)</button>
                            </Typography>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow style={{backgroundColor:'lightgray'}}>
                                        {
                                            tableClientHeadNames.map((value, index) => (
                                                <TableCell key = { index } align="left">{ value }</TableCell>
                                            ))
                                        }
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableRow hover>
                                        <TableCell >{row.clientStatus}</TableCell>
                                        <TableCell align="left">{row.company}</TableCell>
                                        <TableCell align="left">{row.gender}</TableCell>
                                        <TableCell align="left">{row.lName}</TableCell>
                                        <TableCell align="left">{row.fName}</TableCell>
                                        <TableCell align="left">{row.phone}</TableCell>
                                        <TableCell align="left">{row.email}</TableCell>
                                        <TableCell align="left">{row.address}</TableCell>
                                        <TableCell align="left">{row.cp}</TableCell>
                                        <TableCell align="left">{row.city}</TableCell>
                                    </TableRow>
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </React.Fragment>
    );
}

class BusinessTable extends React.Component {
    render() {
        return (
            <TableContainer component={Paper}>
                <Table aria-label="collapsible table">
                    <TableHead>
                        <TableRow style={{backgroundColor:'gray'}}>
                            <TableCell />
                            {
                                tableHeadNames.map((value, index) => (
                                    <TableCell key = { index } align="left">{ value }</TableCell>
                                ))
                            }
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows.map((row) => (
                            <Row key={row.requestNumber} row={row} />
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }

}

export default BusinessTable;
