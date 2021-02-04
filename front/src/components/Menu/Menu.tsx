import React from "react";
import {
    Route,
    HashRouter,
    Link
} from "react-router-dom";
import NouvelleDemande from "../NouvelleDemande/NouvelleDemande";
import ListeDemandes from "../ListeDemandes/ListeDemandes";

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
                <Link to="/liste">
                    <button type="button">
                        Liste de demandes
                    </button>
                </Link>
                <Link to="/nouvelle_demande">
                    <button type="button">
                        Nouvelle demande
                    </button>
                </Link>

            </div>
        );
    }
}


export default Menu;
