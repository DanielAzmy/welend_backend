create or replace function get_user_session(in_id integer)
returns json
as
$$
declare
	result json;
	sessionObj public.session;
begin
	select * into sessionObj
	from public.session s
	where s.user_id = in_id and s.status = 'VALID'
	ORDER BY id DESC
	limit 1;

	if sessionObj.id is not null then
		result := json_build_object('status', 'Success', 'message', 'Returned Successfully','data', array_to_json(array_agg(row_to_json(sessionObj))));
	else
		result := json_build_object('status', 'Error', 'message', 'Returned Failed','data', null);
	end if;
	return result;
end;
$$
language plpgsql;

select * from public.get_user_session(1);