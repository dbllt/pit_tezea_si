import React, {Component} from 'react';
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
import Grid from "@material-ui/core/Grid";


const tableHeadNames = ["Identifiant", "Rôle", ""];

function createRequestData(username: string, authorities: string[]) {
    return {
        username, authorities,
    };
}


interface User {
    id: string;
    username: string;
    authorities: string[]
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


function RedirectionIfNotAdmin() {
    let role = localStorage.getItem('role');
    if (role !=="ADMIN") {
        return <Redirect to="/"/>
    } else {
        return <div/>
    }
}


class UsersScreen extends Component<IProps, IState> {

    state = {
        users: []
    }

    constructor(props: React.ClassAttributes<any>) {
        super(props)
        this.handler = this.handler.bind(this)
    }

    row(props: {
        row: ReturnType<typeof createRequestData>,
        handler: () => void
    }) {
        const {row, handler} = props;

        function removeUser(name: string) {
            API.removeUserByUsername(name).then(() => {
                    handler();
                }
            )
        }

        function DisplayRemove(username: string) {
            if (username !== API.getUsername()) {
                return <IconButton
                    aria-label="expand row"
                    size="small"
                    onClick={() => removeUser(row.username)}
                >
                    <CancelOutlinedIcon style={{color: "red"}}/>
                </IconButton>
            } else {
                return <p></p>
            }
        }

        return (
            <React.Fragment>
                <TableRow hover>
                    <TableCell align="center">{row.username}</TableCell>
                    <TableCell align="center">{row.authorities[0]}</TableCell>
                    <TableCell align="center" style={{width: "10%"}}>
                        {DisplayRemove(row.username)}


                    </TableCell>
                </TableRow>
            </React.Fragment>
        )
            ;
    }

    handler() {
        this.loadUsers()
    }

    componentDidMount() {
        this.loadUsers()
    }

    loadUsers() {
        API.getUsers().then((data => {
            this.setState({users: data})
        }));

    }


    render() {
        return (
            <div className={"users"}>
                <RedirectionIfNotConnected/>
                <RedirectionIfNotAdmin/>
                <TableContainer component={Paper}>
                    <Table aria-label="collapsible table">
                        <TableHead>
                            <TableRow style={{backgroundColor: '#01a1e4'}}>
                                {
                                    tableHeadNames.map((value, index) => (
                                        <TableCell key={index} style={{fontWeight:"bold"}}align="center">{value}</TableCell>
                                    ))
                                }
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.state.users.map((user: User) => (
                                <this.row key={user.id} row={user} handler={this.handler}/>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
                <Grid container direction="column" justify="center" alignItems="center" spacing={4}>
                    <Grid item>
                        <Link to="/addUser">
                            <Button type="button" style={{marginTop: 50, backgroundColor: "#8fbe40",color: 'white',padding:15}}>
                                Ajouter utilisateur
                            </Button>
                        </Link>
                    </Grid>
                    <Grid item>
                        <Link to="/">
                            <Button color="primary">
                                Retour
                            </Button>
                        </Link>
                    </Grid>
                </Grid>
            </div>

        );
    }

}

export default UsersScreen;
