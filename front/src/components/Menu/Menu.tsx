import React from "react";
import {
    Link
} from "react-router-dom";
import {Button} from '@material-ui/core';
import API from "../../network/API";

interface IProps {

}

interface IState {
}

class Menu extends React.Component<IProps, IState> {
    constructor(props: IProps) {
        super(props);
        this.state = {};

    }

    render() {
        var usersList;
        if (API.getRole() === "serge"||true) {//TODO remove true
            usersList = <Link to="/users" style={{margin: 20}}>
                <Button variant="contained">
                    Liste utilisateurs
                </Button>
            </Link>
            ;
        } else {
            usersList = <div/>;
        }


        return (
            <div style={{margin: 50}}>
                <Link to="/requestsList" style={{margin: 20}}>
                    <Button variant="contained">
                        Liste de demandes
                    </Button>
                </Link>
                <Link to="/serviceList" style={{margin: 20}}>
                    <Button variant="contained">
                        Nouvelle demande
                    </Button>
                </Link>
                {usersList}

            </div>
        );
    }
}


export default Menu;
