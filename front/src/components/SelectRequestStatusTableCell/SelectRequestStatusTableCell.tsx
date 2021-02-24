import React, { Component } from 'react';
import TableCell from '@material-ui/core/TableCell';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import API from "../../network/API";


interface Props {
    status: string;
    id: string;
    updateStatus: (name: string, id: string) => void;
}

interface State {
    status: string;
}

class SelectRequestStatusTableCell extends Component<Props, State> {

    state: State = {
        status: ""
    };

    componentDidMount() {
        this.setState({
            status: this.props.status
        });
    };

    handleChange = (event: React.ChangeEvent<{ value: unknown }>) => {
        this.setState({ status: event.target.value as string });
        this.props.updateStatus(this.state.status, this.props.id);
    };

    render() {
        const choices = API.getRequestStatus();;
        return (
            <TableCell className={"noUglyBorder"} align={"center"}>
                <FormControl>
                    <Select
                        value={this.state.status}
                        onChange={this.handleChange}
                        inputProps={{ 'aria-label': 'Without label' }}
                    >
                        {choices.map((value, index) => (<MenuItem key={index} value={value}>{value}</MenuItem>))}
                    </Select>
                </FormControl>
            </TableCell>
        );
    }
}

export default SelectRequestStatusTableCell;