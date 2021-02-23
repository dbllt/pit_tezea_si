import React, {Component, createRef} from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {Box, Button, Collapse, IconButton, TextField, Typography} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import KeyboardArrowDownIcon from "@material-ui/icons/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@material-ui/icons/KeyboardArrowUp";
import API from "../../network/API";
import {Link} from "react-router-dom";
import "./BusinessTable.css"
import SelectRequestStatusTableCell from '../SelectRequestStatusTableCell/SelectRequestStatusTableCell';
import BusinessTableFilter, {Filter} from '../BusinessTableFilter/BusinessTableFilter';


const useRowStyles = makeStyles({
    root: {
        "& > *": {
            borderBottom: '1px solid black',
        }
    }
});

const tableHeadNames = ["N° Demande", "Date de réalisation", "Type Client", "Nom Client", 'Site', "Concierge", 'Statut Demande', "Description"];
const tableClientHeadNames = ["Type Client", "Entreprise", "Civilité", "Nom", "Prénom", "Téléphone", "Email", "Code postal", "Ville", "Détails"];


interface Request {
    id: string;
    date: string,
    hour: string,
    concierge: string,
    site: string,
    serviceType: string,
    requestStatus: string,
    requestAssignment: string,
    executionDate: Date,
    clientStatus: string,
    company: string,
    gender: string,
    lName: string,
    fName: string,
    phone: string,
    email: string,
    address: string,
    cp: string,
    city: string
}

function Row(props: { row: Request, updateStatus: (name: string, id: string) => void }) {
    const {row} = props;
    const [open, setOpen] = React.useState(false);
    const classes = useRowStyles();
    const options = {year: 'numeric', month: 'numeric', day: 'numeric'}
    const executionDate = row.executionDate.toLocaleDateString('FR', options);

    const chooseRowEmergencyStyle = () => {
        const sevenDays = 7 * 24 * 3600 * 1000;
        const fourteenDays = 14 * 24 * 3600 * 1000;
        const dateNow = new Date().getTime();
        const execDate = row.executionDate.getTime();

        return ((execDate - dateNow) <= sevenDays) ? "high_emergency_style_class" :
            ((execDate - dateNow) <= fourteenDays) ? "medium_emergency_style_class" : '';
    }


    return (
        <React.Fragment>
            <TableRow className={chooseRowEmergencyStyle()}>
                <TableCell>
                    <IconButton
                        aria-label="expand row"
                        size="small"
                        onClick={() => setOpen(!open)}
                    >
                        {open ? <KeyboardArrowUpIcon/> : <KeyboardArrowDownIcon/>}
                    </IconButton>
                </TableCell>
                <TableCell align="left">{row.id}</TableCell>
                <TableCell align="left">{executionDate}</TableCell>
                <TableCell align="left">{row.clientStatus}</TableCell>
                <TableCell align="left">{row.fName}</TableCell>
                <TableCell align="left">{row.site}</TableCell>
                <TableCell align="left">{row.concierge}</TableCell>
                <SelectRequestStatusTableCell key={row.id} status={row.requestStatus} id={row.id}
                                              updateStatus={props.updateStatus}/>
                <TableCell align="left">{row.requestAssignment}</TableCell>
            </TableRow>
            <TableRow className={classes.root}>
                <TableCell style={{paddingBottom: 0, paddingTop: 0}} colSpan={10}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box margin={1}>
                            <Typography variant="h6" gutterBottom component="div">
                                Détails
                                <Link to={{
                                    pathname: '/request',
                                    state: {
                                        service: row.site,
                                        requestId: row.id

                                    }
                                }} style={{margin: 20}}>
                                    <Button variant="contained">
                                        Editer
                                    </Button>
                                </Link>
                            </Typography>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow style={{backgroundColor: 'lightgray'}}>
                                        {
                                            tableClientHeadNames.map((value, index) => (
                                                <TableCell key={index} align="left">{value}</TableCell>
                                            ))
                                        }
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableRow hover>
                                        <TableCell>{row.clientStatus}</TableCell>
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

interface IProps {
}

interface IState {
    requests: Request[];
}


class BusinessTable extends Component<IProps, IState> {
    state: IState = {
        requests: []
    };

    applyFilter = (filter: Filter) => {
        API.getRequests(filter).then(data => {
            this.setState({requests: data})
        });
    }

    updateStatus = (name: string, id: string) => {
        // TO-DO connecter à l'api
    };


    render() {
        return (
            <div>

                <BusinessTableFilter applyFilter={this.applyFilter}></BusinessTableFilter>
                <TableContainer component={Paper}>
                    <Table aria-label="collapsible table">
                        <TableHead>
                            <TableRow style={{backgroundColor: 'gray'}}>
                                <TableCell/>
                                {
                                    tableHeadNames.map((value, index) => (
                                        <TableCell key={index} align="left">{value}</TableCell>
                                    ))
                                }
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.state.requests.map((row: Request) => (
                                <Row key={row.id} row={row} updateStatus={this.updateStatus}/>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        );
    }

}

export default BusinessTable;
