import { Role } from './role';

export class User {
    id: number;
    username: string;
    password: string;
    roles?: Role[];
    token?: string;
}
