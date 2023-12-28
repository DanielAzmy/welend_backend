create or replace function create_new_session(in_token varchar, in_user_id integer, in_creation_date timestamp, in_status varchar)
returns json
as
$$
declare
	result json;
begin
	INSERT INTO public.session(token, user_id, creation_date, status)
	VALUES (in_token, in_user_id, in_creation_date, in_status);

	result := json_build_object('status', 'Success', 'message', 'Inserted Successfully', 'data', null);
	return result;
end;
$$
language plpgsql;

-- select public.insert_new_session('minaemadsdfdasfdsa', 1, '2023-01-01', 'VALID');

