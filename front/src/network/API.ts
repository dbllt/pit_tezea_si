
interface Request {
    id: string;
    task: string;
    address: string;
}

interface Client {
    id: string;
    name: string,
    mail: string
}

let requests: Request[] = [];
let clients: Client[] = [];
const API = {

    test: async function():Promise<string> {
        let res:string = "";
        await fetch("https://pokeapi.co/api/v2/pokemon/ditto")
            .then(res => res.json()).then((data) => {
                res = data["id"]
            }).catch(console.log);
        return res;
    },


    login: async function(email: string, password: string):Promise<any>  {
        return true;
    },
    register: async function(email: string, password: string):Promise<any>  {
        return true;
    },
    getRequests: async function():Promise<Request[]>  {
        return requests;
    },
    addRequest: async function(request: Request):Promise<any>  {
        requests.push(request);
    },
    getRequest: async function(id: string):Promise<any>  {
        requests.forEach(function (request) {
            if (request.id === id) {
                return request;
            }
        });
        return false;
    },
    editRequest: async function(id: string, request: Request):Promise<any>  {
        let index = requests.findIndex(x => x.id === id);
        if (index > -1) {
            requests.splice(index, 1);
            requests.push(request);
        }
    },
    removeRequest: async function(id: string):Promise<any>  {
        let index = requests.findIndex(x => x.id === id);
        if (index > -1) {
            requests.splice(index, 1);
        }
    },
    getClients: async function():Promise<Client[]>  {
        return clients;
    },
    addClient: async function(client: Client):Promise<any>  {
        clients.push(client);
    },

    getClient: async function(id: string):Promise<any>  {
        clients.forEach(function (client) {
            if (client.id === id) {
                return client;
            }
        });
        return false;
    },

    editClient: async function(id: string, client: Client):Promise<any> {
        let index = clients.findIndex(x => x.id === id);
        if (index > -1) {
            clients.splice(index, 1);
            clients.push(client);
        }
    },

    removeClient: async function(id: string):Promise<any> {
        let index = clients.findIndex(x => x.id === id);
        if (index > -1) {
            clients.splice(index, 1);
        }
    },


}

export default API