
import React, { Component, createRef } from 'react';
import { TextField, MenuItem, FormControl, Select, Grid, InputLabel } from "@material-ui/core";
import { KeyboardDatePicker, MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
import API from "../../network/API";


export interface Filter {
    clientName: string,
    phoneNumber: string,
    localization: string,
    requestStatus: string,
    site: string,
    urgency: string,
    startDate: Date | null,
    endDate: Date | null,
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
        filter: {
            clientName: "",
            phoneNumber: "",
            localization: "",
            requestStatus: "",
            site: "",
            urgency: "",
            startDate: null,
            endDate: null,
            requestObject: ""
        }
    };

    testStyle = {
        big: {
            backgroundColor: 'red',
        },
    };

    private readonly clientName: React.RefObject<any>;
    private readonly phoneNumber: React.RefObject<any>;
    private readonly localization: React.RefObject<any>;
    private readonly requestObject: React.RefObject<any>;

    constructor(props: Props) {
        super(props);

        this.clientName = createRef();
        this.phoneNumber = createRef();
        this.localization = createRef();
        this.requestObject = createRef();

        this.getPhoneNumber = this.getPhoneNumber.bind(this);
        this.getLocalization = this.getLocalization.bind(this);
        this.getRequestObject = this.getRequestObject.bind(this);
        this.refresh = this.refresh.bind(this);
    }


    componentDidMount() {
        this.props.applyFilter(this.state.filter);
    }

    updateFilter(filter: Filter) {
        this.setState({ filter: filter });
        this.props.applyFilter(filter);
    }
    refresh() {
        const filter: Filter = {
            clientName: this.getClientName(),
            phoneNumber: this.getPhoneNumber(),
            localization: this.getLocalization(),
            requestStatus: this.getRequestStatus(),
            site: this.getSite(),
            urgency: this.getUrgency(),
            startDate: this.getStartDate(),
            endDate: this.getEndDate(),
            requestObject: this.getRequestObject()
        }
        this.updateFilter(filter);
    }

    refreshStartDate = (date: Date | null) => {
        const filter: Filter = {
            clientName: this.getClientName(),
            phoneNumber: this.getPhoneNumber(),
            localization: this.getLocalization(),
            requestStatus: this.getRequestStatus(),
            site: this.getSite(),
            urgency: this.getUrgency(),
            startDate: date,
            endDate: this.getEndDate(),
            requestObject: this.getRequestObject()
        }
        this.updateFilter(filter);
    }

    refreshEndDate = (date: Date | null) => {
        const filter: Filter = {
            clientName: this.getClientName(),
            phoneNumber: this.getPhoneNumber(),
            localization: this.getLocalization(),
            requestStatus: this.getRequestStatus(),
            site: this.getSite(),
            urgency: this.getUrgency(),
            startDate: this.getStartDate(),
            endDate: date,
            requestObject: this.getRequestObject()
        }
        this.updateFilter(filter);
    }

    refreshStatus = (event: React.ChangeEvent<{ value: unknown }>) => {
        const filter: Filter = {
            clientName: this.getClientName(),
            phoneNumber: this.getPhoneNumber(),
            localization: this.getLocalization(),
            requestStatus: event.target.value as string,
            site: this.getSite(),
            urgency: this.getUrgency(),
            startDate: this.getStartDate(),
            endDate: this.getEndDate(),
            requestObject: this.getRequestObject()
        }
        this.updateFilter(filter);
    };

    refreshSite = (event: React.ChangeEvent<{ value: unknown }>) => {
        const filter: Filter = {
            clientName: this.getClientName(),
            phoneNumber: this.getPhoneNumber(),
            localization: this.getLocalization(),
            requestStatus: this.getRequestStatus(),
            site: event.target.value as string,
            urgency: this.getUrgency(),
            startDate: this.getStartDate(),
            endDate: this.getEndDate(),
            requestObject: this.getRequestObject()
        }
        this.updateFilter(filter);
    };

    refreshUrgency = (event: React.ChangeEvent<{ value: unknown }>) => {
        const filter: Filter = {
            clientName: this.getClientName(),
            phoneNumber: this.getPhoneNumber(),
            localization: this.getLocalization(),
            requestStatus: this.getRequestStatus(),
            site: this.state.filter.site,
            urgency: event.target.value as string,
            startDate: this.getStartDate(),
            endDate: this.getEndDate(),
            requestObject: this.getRequestObject()
        }
        this.updateFilter(filter);
    };

    getClientName(): string {
        if (this.clientName.current == null) {
            return "";
        } else {
            return this.clientName.current.value;
        }
    }

    getPhoneNumber(): string {
        if (this.phoneNumber.current == null) {
            return "";
        } else {
            return this.phoneNumber.current.value;
        }
    }

    getLocalization(): string {
        if (this.localization.current == null) {
            return "";
        } else {
            return this.localization.current.value;
        }
    }

    getRequestStatus(): string {
        return this.state.filter.requestStatus;
    }

    getSite(): string {
        return this.state.filter.site;
    }

    getUrgency(): string {
        return this.state.filter.urgency;
    }

    getStartDate(): Date | null {
        return this.state.filter.startDate;
    }

    getEndDate(): Date | null {
        return this.state.filter.endDate;
    }

    getRequestObject(): string {
        if (this.requestObject.current == null) {
            return "";
        } else {
            return this.requestObject.current.value;
        }
    }


    render() {
        const sites = API.getServices();
        const urgencies = API.getUrgencyStatus();
        const requestStatus = API.getRequestStatus();

        return (
            <div style={{ marginRight: 5, marginLeft: 5 }}>
                <Grid container spacing={1} direction="row" justify="space-evenly" alignItems="center">
                    <Grid item>
                        <TextField
                            label="Nom client"
                            inputRef={this.clientName}
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                            onChange={() => this.refresh()}
                        />
                    </Grid>

                    <Grid item>
                        <TextField
                            label="Télphone client"
                            inputRef={this.phoneNumber}
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                            onChange={() => this.refresh()}
                        />
                    </Grid>

                    <Grid item>
                        <TextField
                            label="Localisation client"
                            inputRef={this.localization}
                            id="outlined-margin-normal"
                            margin="normal"
                            variant="outlined"
                            onChange={() => this.refresh()}
                        />
                    </Grid>
                </Grid>

                <Grid container spacing={1} direction="row" justify="space-evenly" alignItems="center">
                    <Grid item xs>
                        <FormControl variant="outlined" fullWidth>
                            <InputLabel id="status-label">Statut demande</InputLabel>
                            <Select
                                value={this.state.filter.requestStatus}
                                onChange={this.refreshStatus}
                                displayEmpty
                                autoWidth={true}
                                label="Statut demande"
                            >
                                <MenuItem value="">Tous Empty</MenuItem>
                                {requestStatus.map((value, index) => (<MenuItem key={index} value={value}>{value}</MenuItem>))}
                            </Select>
                        </FormControl>
                    </Grid>

                    <Grid item xs>
                        <FormControl variant="outlined" fullWidth>
                            <InputLabel>Site demande</InputLabel>
                            <Select
                                value={this.state.filter.site}
                                onChange={this.refreshSite}
                                displayEmpty
                                label="Site demande"
                                autoWidth={true}

                            >
                                <MenuItem value="">Tous Empty</MenuItem>
                                {sites.map((value, index) => (<MenuItem key={index} value={value}>{value}</MenuItem>))}
                            </Select>
                        </FormControl>
                    </Grid>

                    <Grid item xs>
                        <FormControl variant="outlined" style={{minWidth: '400'}}>
                            <InputLabel>Urgence demande</InputLabel>
                            <Select
                                value={this.state.filter.urgency}
                                onChange={this.refreshUrgency}
                                displayEmpty
                                label="Urgence label"
                                autoWidth={true}
                            >
                                <MenuItem value="">Tous Empty</MenuItem>
                                {urgencies.map((value, index) => (<MenuItem key={index} value={value}>{value}</MenuItem>))}
                            </Select>
                        </FormControl>

                    </Grid>

                {/*    <Grid item xs> TODO FIX THIS WITH BACK*/}
                {/*        <TextField*/}
                {/*            label="Objet demande"*/}
                {/*            inputRef={this.requestObject}*/}
                {/*            id="outlined-margin-normal"*/}
                {/*            margin="normal"*/}
                {/*            variant="outlined"*/}
                {/*            onChange={() => this.refresh()}*/}
                {/*        />*/}
                {/*    </Grid>*/}
                </Grid>

                <Grid container spacing={1} direction="row" justify="center" alignItems="center">

                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
                        <Grid item>
                            <KeyboardDatePicker
                                disableToolbar
                                variant="inline"
                                format="dd/MM/yyyy"
                                margin="normal"
                                id="date-picker-inline"
                                label="À partir de ..."
                                value={this.getStartDate()}
                                onChange={this.refreshStartDate}
                                KeyboardButtonProps={{
                                    'aria-label': 'change date',
                                }}
                            />
                        </Grid>

                        <Grid item>
                            <KeyboardDatePicker
                                disableToolbar
                                variant="inline"
                                format="dd/MM/yyyy"
                                margin="normal"
                                id="date-picker-inline"
                                label="Jusqu'à"
                                value={this.getEndDate()}
                                onChange={this.refreshEndDate}
                                KeyboardButtonProps={{
                                    'aria-label': 'change date',
                                }}
                            />
                        </Grid>
                    </MuiPickersUtilsProvider>
                </Grid>
            </div >
        )
    }
}

export default BusinessTableFilter;
