import React, {Component} from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {Button} from "@material-ui/core";
import {Link, Redirect} from "react-router-dom";
import "./UsersScreen.css";
import API from "../../network/API";


const tableHeadNames = ["Identifiant", "Rôle"];

function createRequestData(username: string, role: string) {
    return {
        username, role,
    };
}

function Row(props: { row: ReturnType<typeof createRequestData> }) {
    const {row} = props;

    return (
        <React.Fragment>
            <TableRow hover>
                <TableCell align="center">{row.username}</TableCell>
                <TableCell align="center">{row.role}</TableCell>
            </TableRow>
        </React.Fragment>
    );
}


interface User {
    id: string;
    username: string;
    role: string;
}


interface IProps {
}

interface IState {
    users: User[];
}

function RedirectionIfNotConnected() {
    let temp = localStorage.getItem('token');
    if (temp === null) {
        temp = "";
    }
    let token: string = temp;
    if (token==="") {
        return <Redirect to="/login"/>
    }else{
        return <div/>
    }
}

class UsersScreen extends Component<IProps, IState> {
    state = {
        users: []
    }

    componentDidMount() {
        API.getUsers().then((data => {
            this.setState({users: data})
        }));
    }


    render() {
        return (
            <div className={"users"}>
                <RedirectionIfNotConnected/>
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
                            {this.state.users.map((user: User) => (
                                <Row key={user.id} row={user}/>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
                <Link to="/addUser">
                    <Button type="button">
                        Ajouter utilisateur
                    </Button>
                </Link>
                <Link to="/">
                    <Button color="primary">
                        Retour
                    </Button>
                </Link>
            </div>

        );
    }

}

export default UsersScreen;