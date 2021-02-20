import React, {Component, createRef} from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {Button, IconButton} from "@material-ui/core";
import {Link, Redirect} from "react-router-dom";
import "./UsersScreen.css";
import API from "../../network/API";
import CancelOutlinedIcon from '@material-ui/icons/CancelOutlined';
import KeyboardArrowUpIcon from "@material-ui/icons/KeyboardArrowUp";
import KeyboardArrowDownIcon from "@material-ui/icons/KeyboardArrowDown";


const tableHeadNames = ["Identifiant", "Rôle", ""];

function createRequestData(username: string, role: string) {
    return {
        username, role,
    };
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
    if (token === "") {
        return <Redirect to="/login"/>
    } else {
        return <div/>
    }
}


class UsersScreen extends Component<IProps, IState> {

    row(props: { row: ReturnType<typeof createRequestData> }) {
        const {row} = props;

        return (
            <React.Fragment>
                <TableRow hover>
                    <TableCell align="center">{row.username}</TableCell>
                    <TableCell align="center">{row.role}</TableCell>
                    <TableCell align="center">

                        <IconButton
                            aria-label="expand row"
                            size="small"
                            //onClick={() => this.removeUser(row.username)}
                            //TODO fix this
                        >
                            <CancelOutlinedIcon/>
                        </IconButton>
                    </TableCell>
                </TableRow>
            </React.Fragment>
        )
            ;
    }


    constructor(props: React.Props<any>) {
        super(props)
        this.removeUser = this.removeUser.bind(this)
    }

    state = {
        users: []
    }


    removeUser(name: string) {
        API.removeUser(name).then(() => this.setState({}))
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
                                <this.row key={user.id} row={user}/>
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