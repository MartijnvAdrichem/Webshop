export class Account{
  id: number;
  firstname: string;
  prefix: string;
  lastname: string;
  username: string;
  password: string;
  password2: string;
  isAdmin: boolean = false;
  isActive: boolean = true;
  inactiveProducts: boolean;
  inactiveClients: boolean;
}
