interface Request {
    id: string;
    task: string;
    adress: string;
}

interface Client {
    id: string;
    name: string,
    mail: string
}

let requests: Request[] = [];
let clients: Client[] = [];
const API = {


    login: function async(email: string, password: string) {
        return true;
    },
    register: function async(email: string, password: string) {
        return true;
    },
    getRequests: function async() {
        return requests;
    },
    addRequest: function async(request: Request) {
        requests.push(request);
    },
    getRequest: function async(id: string) {
        requests.forEach(function (request) {
            if (request.id === id) {
                return request;
            }
        });
        return false;
    },
    editRequest: function async(id: string, request: Request) {
        var index =  requests.findIndex(x => x.id===id);
        if (index > -1) {
            requests.splice(index, 1);
            requests.push(request);
        }
    },
    removeRequest: function async(id: string) {
        var index =  requests.findIndex(x => x.id===id);
        if (index > -1) {
            requests.splice(index, 1);
        }
    },
    getClients: function async() {
        return clients;
    },
    addClient: function async(client: Client) {
        clients.push(client);
    },

    getClient: function async(id: string) {
        clients.forEach(function (client) {
            if (client.id === id) {
                return client;
            }
        });
        return false;
    },

    editClient: function async(id: string, client: Client) {
        var index =  clients.findIndex(x => x.id===id);
        if (index > -1) {
            clients.splice(index, 1);
            clients.push(client);
        }
    },

    removeClient: function async(id: string) {
        var index =  clients.findIndex(x => x.id===id);
        if (index > -1) {
            clients.splice(index, 1);
        }
    },


}

export default API