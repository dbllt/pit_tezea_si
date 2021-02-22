import React from "react";
import {Link, Redirect} from "react-router-dom";
import {Button} from '@material-ui/core';
import API from "../../network/API";
import styles from './Menu.module.css';

interface IProps {

}

interface IState {
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

class Menu extends React.Component<IProps, IState> {
    constructor(props: IProps) {
        super(props);
        this.state = {};

    }

    render() {
        let usersList;
        if (API.getRole() === "ADMIN") {
            usersList = <Link to="/users">
                <Button variant="contained" className={styles.MyButton}>
                    Liste utilisateurs
                </Button>
            </Link>

        } else {
            usersList = <div/>;
        }


        return (
            <div className={styles.MyDiv}>
                <RedirectionIfNotConnected/>
                <Link to="/requestsList">
                    <Button variant="contained" className={styles.MyButton}>
                        Liste de demandes
                    </Button>
                </Link>
                <Link to="/serviceList">
                    <Button variant="contained" className={styles.MyButton}>
                        Nouvelle demande
                    </Button>
                </Link>
                {usersList}

            </div>
        );
    }
}


export default Menu;
