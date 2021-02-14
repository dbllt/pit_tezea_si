import React from "react";
import {
    Link
} from "react-router-dom";
import {Button} from '@material-ui/core';

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
                <Link to="/users" style={{margin: 20}}>
                    <Button variant="contained">
                        Liste utilisateurs
                    </Button>
                </Link>

            </div>
        );
    }
}


export default Menu;
