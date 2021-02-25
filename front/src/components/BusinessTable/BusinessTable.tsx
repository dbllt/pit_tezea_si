import React, {Component} from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {Box, Button, Collapse, IconButton, Typography} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import KeyboardArrowDownIcon from "@material-ui/icons/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@material-ui/icons/KeyboardArrowUp";
import API, {PatchRequest} from "../../network/API";
import {Link} from "react-router-dom";
import "./BusinessTable.css"
import SelectRequestStatusTableCell from '../SelectRequestStatusTableCell/SelectRequestStatusTableCell';
import BusinessTableFilter, {Filter} from '../BusinessTableFilter/BusinessTableFilter';
import BusinessTableSort, {Sort} from '../BusinessTableSort/BusinessTableSort';

const useRowStyles = makeStyles({
    root: {
        "& > *": {
            borderBottom: '1px solid black',
        }
    }
});

const tableHeadNames = ["N° Demande", "Date de réalisation", "Type Client", "Nom Client", 'Site', "Concierge", 'Statut Demande', "Description"];
const tableClientHeadNames = ["Type Client", "Entreprise", "Civilité", "Nom", "Prénom", "Téléphone", "Email", "Adresse", "Code Postal", "Ville"];


interface Request {
    id: string,
    concierge: string,
    site: string,
    typeRequest: string,
    requestDesc: string,
    numberPerson: string,
    place: string,
    regularity: string,
    duration: string,
    material: string [],
    materialother: string,
    internalInfo: string,
    executionDate: string,
    executionTime: string,
    requestStatus: string,
    requestAssignment: string,
    images: File [],
    photos: string[],
    client: IClient
}

interface IClient {
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

function Row(props: { row: Request, urgency: string | undefined, updateStatus: (name: string, id: string) => void }) {
    const {row} = props;
    const [open, setOpen] = React.useState(false);
    const classes = useRowStyles();

    const executionDate = row.executionDate;


    function formatDate(d: string) {
        var t = d.split("-");
        return t[1] + "/" + t[0] + "/" + t[2]
    }

    const chooseRowEmergencyStyle = () => {
        const sevenDays = 7 * 24 * 3600 * 1000;
        const fourteenDays = 14 * 24 * 3600 * 1000;
        const dateNow = new Date().getTime();
        const x = formatDate(row.executionDate);
        const temp = new Date(x);
        const execDate = temp.getTime();

        return ((execDate - dateNow) <= sevenDays) ? "high_emergency_style_class" :
            ((execDate - dateNow) <= fourteenDays) ? "medium_emergency_style_class" : "low_emergency_style_class";
    }

    function DisplayRow() {
        return <React.Fragment>
            <TableRow className={chooseRowEmergencyStyle()}>
                <TableCell className={"noUglyBorder"} align={"center"}>
                    <IconButton
                        aria-label="expand row"
                        size="small"
                        onClick={() => setOpen(!open)}
                    >
                        {open ? <KeyboardArrowUpIcon/> : <KeyboardArrowDownIcon/>}
                    </IconButton>
                </TableCell>
                <TableCell className={"test"} align="center">{row.id}</TableCell>
                <TableCell className={"noUglyBorder"} align="center">{executionDate}</TableCell>
                <TableCell className={"noUglyBorder"} align="center">{row.client.clientStatus}</TableCell>
                <TableCell className={"noUglyBorder"} align="center">{row.client.fName}</TableCell>
                <TableCell className={"noUglyBorder"} align="center">{row.site}</TableCell>
                <TableCell className={"noUglyBorder"} align="center">{row.concierge}</TableCell>
                <SelectRequestStatusTableCell key={row.id} status={row.requestStatus} id={row.id}
                                              updateStatus={props.updateStatus}/>
                <TableCell className={"noUglyBorder"} align="center"><p style={{
                    textAlign: "center",
                    maxWidth: 100,
                    overflow: "hidden",
                    maxHeight: 13
                }}>{row.requestDesc}</p></TableCell>
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
                                        Éditer
                                    </Button>
                                </Link>
                            </Typography>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow>
                                        {
                                            tableClientHeadNames.map((value, index) => (
                                                <TableCell key={index} align="left"
                                                           style={{fontWeight: "bold"}}>{value}</TableCell>
                                            ))
                                        }
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableRow hover>
                                        <TableCell>{row.client.clientStatus}</TableCell>
                                        <TableCell align="left">{row.client.company}</TableCell>
                                        <TableCell align="left">{row.client.gender}</TableCell>
                                        <TableCell align="left">{row.client.lName}</TableCell>
                                        <TableCell align="left">{row.client.fName}</TableCell>
                                        <TableCell align="left">{row.client.phone}</TableCell>
                                        <TableCell align="left">{row.client.email}</TableCell>
                                        <TableCell align="left">{row.client.address}</TableCell>
                                        <TableCell align="left">{row.client.cp}</TableCell>
                                        <TableCell align="left">{row.client.city}</TableCell>
                                    </TableRow>
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </React.Fragment>
    }


    if (props.urgency === "Alerte Rouge") {
        if (chooseRowEmergencyStyle() === "high_emergency_style_class") {
            if (API.getRole() === "Responsable Site") {
                if (API.getSite() === row.site) {
                    return DisplayRow()
                } else {
                    return <div/>
                }
            } else {
                return DisplayRow()
            }
        } else {
            return <div/>
        }
    } else if (props.urgency === "Alerte orange") {
        if (chooseRowEmergencyStyle() === "medium_emergency_style_class") {
            if (API.getRole() === "Responsable Site") {
                if (API.getSite() === row.site) {
                    return DisplayRow()
                } else {
                    return <div/>
                }
            } else {
                return DisplayRow()
            }
        } else {
            return <div/>
        }
    } else if (props.urgency === "Normale") {
        if (chooseRowEmergencyStyle() === "low_emergency_style_class") {
            if (API.getRole() === "Responsable Site") {
                if (API.getSite() === row.site) {
                    return DisplayRow()
                } else {
                    return <div/>
                }
            } else {
                return DisplayRow()
            }
        } else {
            return <div/>
        }
    } else {
        if (API.getRole() === "Responsable Site") {
            if (API.getSite() === row.site) {
                return DisplayRow()
            } else {
                return <div/>
            }
        } else {
            return DisplayRow()
        }
    }


}


interface IProps {
}

interface IState {
    requests: Request[];
    urgency: string | undefined;
}


class BusinessTable extends Component<IProps, IState> {
    state: IState = {
        requests: [],
        urgency: undefined
    };

    applyFilter = (filter: Filter) => {
        API.getRequests(filter).then(data => {
            this.setState({requests: data, urgency: filter.urgency})
        });
    }

    applySort = (sort: Sort) => {

    }

    updateStatus = (status: string, id: string) => {
        let request: PatchRequest = {
            id: +id,
            status: status
        }
        API.editRequest(request)
    };


    render() {
        return (
            <div>

                <BusinessTableFilter applyFilter={this.applyFilter}/>
                <BusinessTableSort applySort={this.applySort}/>
                <br/>
                <TableContainer component={Paper}>
                    <Table size="small" aria-label="collapsible table">
                        <TableHead>
                            <TableRow style={{backgroundColor: '#01a1e4', height: "60px"}}>
                                <TableCell className={"noUglyBorder"}/>
                                {
                                    tableHeadNames.map((value, index) => (
                                        <TableCell className={"noUglyBorder"} key={index} align="center" style={{
                                            fontWeight: "bold",
                                            fontSize: 15,
                                            color: "#fffaf0"
                                        }}>{value}</TableCell>
                                    ))
                                }
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.state.requests.map((row: Request) => (
                                <Row key={row.id} row={row} urgency={this.state.urgency}
                                     updateStatus={this.updateStatus}/>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        );
    }

}

export default BusinessTable;
