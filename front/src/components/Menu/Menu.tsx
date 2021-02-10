import React from "react";
import {
    Link
} from "react-router-dom";
import { Button } from '@material-ui/core';

interface Props {

}

interface State {
}

class Menu extends React.Component<Props, State> {
    constructor(props: Props) {
        super(props);
        this.state = {};

    }


    render() {
        return (
            <div>
                <Link to="/requestsList">
                    <Button color="primary">
                        Liste de demandes
                    </Button>
                </Link>
                <Link to="/newRequest">
                    <Button color="primary">
                        Nouvelle demande
                    </Button>
                </Link>

            </div>
        );
    }
}


export default Menu;
