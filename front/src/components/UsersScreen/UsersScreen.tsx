import React, {Component} from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {Button} from "@material-ui/core";
import {Link} from "react-router-dom";
import "./UsersScreen.css";
import API from "../../network/API";


const tableHeadNames = ["Identifiant", "Rôle"];
const rows = [
    createRequestData("Grogu", "Concierge"),
    createRequestData("Serge", "Serge"),
]

function createRequestData(identifiant: string, role: string) {
    return {
        identifiant, role,
    };
}

function Row(props: { row: ReturnType<typeof createRequestData> }) {
    const {row} = props;

    return (
        <React.Fragment>
            <TableRow hover>
                <TableCell align="center">{row.identifiant}</TableCell>
                <TableCell align="center">{row.role}</TableCell>
            </TableRow>
        </React.Fragment>
    );
}


interface Utilisateur {
    id: string;
    identifiant: string;
    role: string;
}


interface IProps {
}

interface IState {
    utilisateurs: Utilisateur[];
}


class UsersScreen extends Component<IProps, IState> {
    state = {
        utilisateurs: []
    }

    componentDidMount() {
        API.getUtilisateurs().then((data => {
            this.setState({utilisateurs: data})
        }));
    }

    render() {
        return (
            <div className={"users"}>
                <TableContainer component={Paper}>
                    <Table aria-label="collapsible table">
                        <TableHead>
                            <TableRow style={{backgroundColor: 'gray'}}>
                                {
                                    tableHeadNames.map((value, index) => (
                                        <TableCell key={index} align="center">{value}</TableCell>
                                    ))
                                }
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.state.utilisateurs.map((utilisateur:Utilisateur) => (
                                <Row key={utilisateur.id} row={utilisateur} />
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
                <Link to="/addUser">
                    <Button type="button">
                        Ajouter utilisateur
                    </Button>
                </Link>
                <Link to="/menu">
                    <Button color="primary">
                        Retour
                    </Button>
                </Link>
            </div>

        );
    }

}

export default UsersScreen;