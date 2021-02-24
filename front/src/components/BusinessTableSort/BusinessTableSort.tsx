
import React, { Component } from 'react';
import { MenuItem, FormControl, Select, Grid } from "@material-ui/core";


export interface Sort {
    sort: string,
    order: string
}

interface Props {
    applySort: (sort: Sort) => void;
}

interface State {
    sort: string,
    order: string
}

class BusinessTableSort extends Component<Props, State> {

    state: State = {
        sort: "Date",
        order: "Descendant"
    };


    createSortFromState = () => {
        const sort: Sort = {
            sort: this.state.sort,
            order: this.state.order
        };
        return sort;
    }

    refreshSort = (event: React.ChangeEvent<{ value: unknown }>) => {
        this.setState({ sort: event.target.value as string });
        this.props.applySort(this.createSortFromState());

    };

    refreshOrder = (event: React.ChangeEvent<{ value: unknown }>) => {
        this.setState({ order: event.target.value as string });
        this.props.applySort(this.createSortFromState());
    };


    render() {
        const possibleSort = ["Date", "Nom du client", "Urgence"];
        const possibleOrder = ["Ascendant", "Descendant"];
        return (
            <div>
                <Grid container spacing={1} direction="row" justify="space-evenly" alignItems="center">
                    <Grid item>
                        <FormControl>
                            <Select
                                value={this.state.sort}
                                onChange={this.refreshSort}
                                displayEmpty
                                inputProps={{ 'aria-label': 'Without label' }}
                            >
                                {possibleSort.map((value, index) => (<MenuItem key={index} value={value}>{value}</MenuItem>))}
                            </Select>
                        </FormControl>
                    </Grid>

                    <Grid item>
                        <FormControl>
                            <Select
                                value={this.state.order}
                                onChange={this.refreshOrder}
                                inputProps={{ 'aria-label': 'Without label' }}
                            >
                                {possibleOrder.map((value, index) => (<MenuItem key={index} value={value}>{value}</MenuItem>))}
                            </Select>
                        </FormControl>
                    </Grid>
                </Grid>
            </div>
        )
    }
}

export default BusinessTableSort;