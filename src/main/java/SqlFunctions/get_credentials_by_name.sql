create or replace function get_credentials_by_name(in_name varchar)
returns json
as
$$
declare
	credsData "creds";
begin
	select * into credsData
	from "creds"
	where name = in_name;
	if found then
		return json_build_object('status', 'success', 'message', 'Returned successfully.', 'data', credsData);
	else
		return json_build_object('status', 'error', 'message', 'Creds not found.', 'data', null);
	end if;
end;
$$
language plpgsql;

select get_credentials_by_name('DOXTER');