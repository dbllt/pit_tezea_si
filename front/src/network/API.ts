
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

interface Utilisateur{
    id:string;
    identifiant:string;
    role:string;
}


const grogu:Utilisateur ={
    id:"0",
    identifiant:"grogu",
    role:"concierge"
}

let requests: Request[] = [];
let clients: Client[] = [];
let utilisateurs: Utilisateur[] = [];
utilisateurs.push(grogu);
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


    getUtilisateurs: async function():Promise<Utilisateur[]>  {
        return utilisateurs;
    },

    addUtilisateur: async function(identifiant:string,role:string):Promise<any>  {
        const utilisateur: Utilisateur = {
            id: utilisateurs.length.toString(),
            identifiant: identifiant,
            role:role
        };

        utilisateurs.push(utilisateur);
    },

    getUtilisateur: async function(id: string):Promise<any>  {
        utilisateurs.forEach(function (utilisateur) {
            if (utilisateur.id === id) {
                return utilisateur;
            }
        });
        return false;
    },

    editUtilisateur: async function(id: string, utilisateur: Utilisateur):Promise<any> {
        let index = utilisateurs.findIndex(x => x.id === id);
        if (index > -1) {
            utilisateurs.splice(index, 1);
            utilisateurs.push(utilisateur);
        }
    },

    removeUtilisateur: async function(id: string):Promise<any> {
        let index = utilisateurs.findIndex(x => x.id === id);
        if (index > -1) {
            utilisateurs.splice(index, 1);
        }
    },


}

export default API