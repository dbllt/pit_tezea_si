
import React, {Component, createRef} from 'react';
import {Box, Button, Collapse, IconButton, TextField, Typography} from "@material-ui/core";

export interface Filter {
    clientName: string,
    phoneNumber: string,
    localization: string,
    requestStatus: string,
    site: string,
    urgency: string,
    startDate: string,
    endDate: string,
    requestObject: string
}

interface Props {
    applyFilter: (filter: Filter) => void;
}

interface State {
    filter: Filter
}

class BusinessTableFilter extends Component<Props, State> {

    state: State = {
        filter : {
            clientName: "",
            phoneNumber: "",
            localization: "",
            requestStatus: "",
            site: "",
            urgency: "",
            startDate: "",
            endDate: "",
            requestObject: ""
        }
    };


    // private readonly clientName: React.RefObject<any>;
    // private readonly phoneNumber: React.RefObject<any>;
    // private readonly localization: React.RefObject<any>;
    // private readonly requestStatus: React.RefObject<any>;
    private readonly site: React.RefObject<any>;
    // private readonly urgency: React.RefObject<any>;
    // private readonly startDate: React.RefObject<any>;
    // private readonly endDate: React.RefObject<any>;
    // private readonly requestObject: React.RefObject<any>;

    constructor(props: Props) {
        super(props);
        this.site = createRef();

        this.getSite = this.getSite.bind(this);
        this.refreshSite = this.refreshSite.bind(this);
    }


    componentDidMount() {
        this.props.applyFilter(this.state.filter);
        // API.getRequests(filter).then((data => {
        //     this.setState({requests: data})
        // }));
    }

    refreshSite() {
        this.setState ({
            filter : {
                clientName: "",
                phoneNumber: "",
                localization: "",
                requestStatus: "",
                site: this.getSite(),
                urgency: "",
                startDate: "",
                endDate: "",
                requestObject: ""
            }
        });
        
        this.props.applyFilter(this.state.filter);

        // API.getRequests(filter).then((data => {
        //     this.setState({requests: data})
        // }));
    }

    getClientName(): string {
        if (this.site.current == null) {
            return "";
        } else {
            return this.site.current.value;
        }
    }

    getSite(): string {
        if (this.site.current == null) {
            return "";
        } else {
            return this.site.current.value;
        }
    }

    render() {
        return (
            <div>
                {/* <TextField
                    label="Nom du client:"
                    inputRef={this.clientName}
                    id="outlined-margin-normal"
                    margin="normal"
                    variant="outlined"
                    onChange={() => this.refreshClientName()}
                />
                <TextField
                    label="Numéro de télphone:"
                    inputRef={this.phoneNumber}
                    id="outlined-margin-normal"
                    margin="normal"
                    variant="outlined"
                    onChange={() => this.refreshPhoneNumber()}
                />
                <TextField
                    label="Localisation:"
                    inputRef={this.localization}
                    id="outlined-margin-normal"
                    margin="normal"
                    variant="outlined"
                    onChange={() => this.refreshLocalization()}
                />
                <TextField
                    label="Statut de la demande:"
                    inputRef={this.requestStatus}
                    id="outlined-margin-normal"
                    margin="normal"
                    variant="outlined"
                    onChange={() => this.refreshRequestStatus()}
                /> */}
                <TextField
                    label="Site:"
                    inputRef={this.site}
                    id="outlined-margin-normal"
                    margin="normal"
                    variant="outlined"
                    onChange={() => this.refreshSite()}
                />
                {/* <TextField
                    label="Urgence:"
                    inputRef={this.site}
                    id="outlined-margin-normal"
                    margin="normal"
                    variant="outlined"
                    onChange={() => this.refreshUrgence()}
                />
                <TextField
                    label="Objet de la demande:"
                    inputRef={this.site}
                    id="outlined-margin-normal"
                    margin="normal"
                    variant="outlined"
                    onChange={() => this.refreshObject()} */}
                {/* /> */}
            </div>
        )
    }
}

export default BusinessTableFilter;
